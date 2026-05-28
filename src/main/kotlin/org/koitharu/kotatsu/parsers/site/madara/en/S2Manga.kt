package org.koitharu.kotatsu.parsers.site.madara.en

import org.jsoup.nodes.Document // Required for Document
import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaChapter // Required for MangaChapter
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.madara.MadaraParser

@MangaSourceParser("S2MANGA", "S2Manga", "en")
internal class S2Manga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.S2MANGA, "s2read.com") {
	
    override val datePattern = "MMMM dd, yyyy"

    // Intercepts the broken mangaUrl caused by S2Read's bad SEO tags
    override suspend fun loadChapters(mangaUrl: String, document: Document): List<MangaChapter> {
        // If the URL was stripped down to just the root, use the actual document URL instead
        val correctUrl = if (mangaUrl == "/" || mangaUrl.isEmpty()) {
            document.baseUri()
        } else {
            mangaUrl
        }
        
        // Pass the corrected URL back to the default Madara ajax handler
        return super.loadChapters(correctUrl, document)
    }
}
