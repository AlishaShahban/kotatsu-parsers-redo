package org.koitharu.kotatsu.parsers.site.madara.en

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.madara.MadaraParser

@MangaSourceParser("S2MANGA", "S2Manga", "en")
internal class S2Manga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.S2MANGA, "s2read.com") {
        
    override val datePattern = "MMMM dd, yyyy"

    // Scenario 1 code is pasted here
    override fun getHeaders(url: String): Headers {
        return super.getHeaders(url).newBuilder().apply {
            add("X-Requested-With", "XMLHttpRequest")
            add("Referer", "https://s2read.com/")
        }.build()
    }

	}

}
