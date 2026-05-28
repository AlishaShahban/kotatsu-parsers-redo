package org.koitharu.kotatsu.parsers.site.madara.en

import org.jsoup.nodes.Document
import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.Manga
import org.koitharu.kotatsu.parsers.model.MangaChapter
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.madara.MadaraParser

@MangaSourceParser("S2MANGA", "S2Manga", "en")
internal class S2Manga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.S2MANGA, "s2read.com") {
	
    override val datePattern = "MMMM dd, yyyy"

    // 1. BYPASS AJAX: Tell the parser to check for chapters directly in the HTML first
    override val selectTestAsync = "li.wp-manga-chapter, div.wp-manga-chapter"

    // 2. FIX AJAX (Fallback): If it absolutely must use AJAX, extract the real URL from the page location
    override suspend fun loadChapters(mangaUrl: String, document: Document): List<MangaChapter> {
        val correctUrl = document.location()
        return super.loadChapters(correctUrl, document)
    }

    // 3. PROTECT DATABASE: Throw away the poisoned S2Read URL so it doesn't break your bookmarks
    override suspend fun getDetails(manga: Manga): Manga {
        val details = super.getDetails(manga)
        return details.copy(
            url = manga.url, 
            publicUrl = manga.publicUrl
        )
    }
}
