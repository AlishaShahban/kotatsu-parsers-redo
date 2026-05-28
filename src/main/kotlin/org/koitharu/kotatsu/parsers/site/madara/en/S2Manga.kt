package org.koitharu.kotatsu.parsers.site.madara.en

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.madara.MadaraParser

@MangaSourceParser("S2READ", "S2Read", "en")
internal class S2Read(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.S2READ, "s2read.com") {
	override val datePattern = "MMMM dd, yyyy"
}
