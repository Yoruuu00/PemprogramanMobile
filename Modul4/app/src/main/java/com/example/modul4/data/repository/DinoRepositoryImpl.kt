package com.example.modul4.data.repository

import com.example.modul4.R
import com.example.modul4.domain.model.Dino
import com.example.modul4.domain.repository.DinoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DinoRepositoryImpl : DinoRepository {

    private val dinos = listOf(
        Dino(
            name = "Tyrannosaurus Rex",
            description = """
                Tyrannosaurus Rex adalah predator terbesar di zamannya, hidup sekitar 68 hingga 66 juta tahun yang lalu di akhir periode Kapur. Dengan panjang mencapai 12 meter dan tinggi hampir 4 meter, T-Rex memiliki rahang sangat kuat dan gigi setajam pisau untuk merobek daging mangsanya.

                Meskipun tangan depannya kecil, tubuhnya yang besar dan kekuatan gigitan luar biasa menjadikannya salah satu dinosaurus paling menakutkan. Fosilnya ditemukan di Amerika Utara dan menjadi ikon dalam dunia paleontologi.
            """.trimIndent(),
            shortDesc = "Predator terbesar di zamannya dengan rahang yang sangat kuat.",
            period = "Akhir Periode Kapur",
            imageRes = R.drawable.tyrannosaurusrex,
            type = "Karnivora",
            wikiUrl = "https://en.wikipedia.org/wiki/Tyrannosaurus"
        ),
        Dino(
            name = "Triceratops",
            description = """
                Triceratops adalah dinosaurus herbivora besar dengan tiga tanduk dan pelindung tengkorak besar, hidup berdampingan dengan T-Rex di akhir Kapur sekitar 68 juta tahun yang lalu. Ia mencapai panjang sekitar 9 meter dan berat hingga 12 ton.

                Tanduk panjangnya diyakini digunakan untuk pertahanan diri dan pertarungan sesama jantan saat musim kawin. Struktur tengkoraknya juga mungkin berfungsi untuk pengaturan suhu tubuh atau sebagai alat komunikasi visual.
            """.trimIndent(),
            shortDesc = "Herbivora besar dengan tiga tanduk ikonik di wajahnya.",
            period = "Akhir Periode Kapur",
            imageRes = R.drawable.triceratops,
            type = "Herbivora",
            wikiUrl = "https://en.wikipedia.org/wiki/Triceratops"
        ),
        Dino(
            name = "Velociraptor",
            description = """
                Velociraptor adalah dinosaurus kecil dan karnivora yang terkenal karena kecepatannya dan diduga memiliki bulu. Ia hidup sekitar 75 hingga 71 juta tahun yang lalu di wilayah yang kini merupakan Mongolia.

                Meski hanya seukuran kalkun, Velociraptor sangat gesit dan cerdas, serta berburu dalam kelompok. Cakar melengkung pada kaki belakangnya digunakan untuk mencengkram dan melumpuhkan mangsa.
            """.trimIndent(),
            shortDesc = "Karnivora kecil yang terkenal karena kecepatan dan kecerdasannya.",
            period = "75-71 Juta Tahun Lalu",
            imageRes = R.drawable.velociraptor,
            type = "Karnivora",
            wikiUrl = "https://en.wikipedia.org/wiki/Velociraptor"
        ),
        Dino(
            name = "Stegosaurus",
            description = """
                Stegosaurus adalah dinosaurus herbivora dari periode Jurassic akhir, terkenal dengan piring tulang besar di punggung dan ekor berduri yang disebut thagomizer. Ia hidup sekitar 155 juta tahun lalu dan memiliki otak relatif kecil.

                Piring punggungnya kemungkinan digunakan untuk menakuti pemangsa, menarik pasangan, atau mengatur suhu tubuh. Ekor berdurinya menjadi senjata efektif melawan predator seperti Allosaurus.
            """.trimIndent(),
            shortDesc = "Herbivora dengan piring tulang besar di punggung dan ekor berduri.",
            period = "Periode Jurassic Akhir",
            imageRes = R.drawable.stegosaurus,
            type = "Herbivora",
            wikiUrl = "https://en.wikipedia.org/wiki/Stegosaurus"
        ),
        Dino(
            name = "Brachiosaurus",
            description = """
                Brachiosaurus adalah salah satu sauropoda terbesar yang hidup sekitar 154 hingga 150 juta tahun lalu pada periode Jurassic. Ciri khasnya adalah leher panjang yang memungkinkan ia meraih daun dari pohon tinggi.

                Berbeda dari sauropoda lain, kaki depan Brachiosaurus lebih panjang daripada kaki belakang, membuat posturnya menjulang tinggi. Ia diperkirakan memiliki berat hingga 40 ton dan panjang lebih dari 25 meter.
            """.trimIndent(),
            shortDesc = "Sauropoda raksasa dengan leher sangat panjang dan kaki depan yang tinggi.",
            period = "Periode Jurassic",
            imageRes = R.drawable.brachiosaurus,
            type = "Herbivora",
            wikiUrl = "https://en.wikipedia.org/wiki/Brachiosaurus"
        ),
        Dino(
            name = "Spinosaurus",
            description = """
                Spinosaurus adalah dinosaurus karnivora terbesar yang hidup sekitar 112–93 juta tahun lalu di Afrika Utara. Ciri utamanya adalah layar punggung tinggi seperti layar kapal, kemungkinan digunakan untuk menampilkan diri atau mengatur suhu.

                Ia diyakini sebagai semi-akuatik, berburu ikan dan hewan air lainnya di sungai purba. Dengan panjang mencapai 15 meter, Spinosaurus lebih besar dari T-Rex dan sangat adaptif terhadap lingkungan air.
            """.trimIndent(),
            shortDesc = "Karnivora semi-akuatik dengan layar punggung yang khas.",
            period = "112-93 Juta Tahun Lalu",
            imageRes = R.drawable.spinosaurus,
            type = "Karnivora",
            wikiUrl = "https://en.wikipedia.org/wiki/Spinosaurus"
        ),
        Dino(
            name = "Ankylosaurus",
            description = """
                Ankylosaurus adalah dinosaurus herbivora dari akhir periode Kapur, terkenal karena tubuhnya yang dilapisi zira dan ekor besar berbentuk gada. Panjangnya mencapai 6 hingga 8 meter dan berat hingga 8 ton.

                Bentuk tubuhnya seperti tank membuatnya hampir kebal terhadap serangan predator. Ekor berotot dan keras dapat digunakan untuk menyerang balik dan melukai lawan secara fatal.
            """.trimIndent(),
            shortDesc = "Herbivora berlapis zirah dengan ekor besar berbentuk gada.",
            period = "Akhir Periode Kapur",
            imageRes = R.drawable.ankylosaurus,
            type = "Herbivora",
            wikiUrl = "https://en.wikipedia.org/wiki/Ankylosaurus"
        ),
        Dino(
            name = "Allosaurus",
            description = """
                Allosaurus adalah predator utama dari periode Jurassic, hidup sekitar 155 hingga 145 juta tahun lalu. Ia memiliki tengkorak besar, gigi tajam, dan tubuh ramping yang memungkinkannya berburu dengan kecepatan tinggi.

                Allosaurus sering disebut 'singa Jurassic' karena perannya sebagai pemburu puncak. Ia mungkin berburu dalam kelompok dan mangsanya termasuk sauropoda besar seperti Diplodocus dan Camarasaurus.
            """.trimIndent(),
            shortDesc = "Predator puncak dari periode Jurassic, sering disebut 'singa Jurassic'.",
            period = "Periode Jurassic Akhir",
            imageRes = R.drawable.allosaurus,
            type = "Karnivora",
            wikiUrl = "https://en.wikipedia.org/wiki/Allosaurus"
        ),
        Dino(
            name = "Diplodocus",
            description = """
                Diplodocus adalah sauropoda raksasa dari periode Jurassic, dikenal karena leher dan ekor super panjang. Panjang tubuhnya bisa mencapai 27 meter, menjadikannya salah satu dinosaurus terpanjang yang pernah hidup.

                Ia hidup di Amerika Utara sekitar 154 juta tahun lalu dan memakan tumbuhan rendah di hutan purba. Ekor panjangnya kemungkinan digunakan untuk pertahanan atau komunikasi sonik seperti cambuk.
            """.trimIndent(),
            shortDesc = "Salah satu dinosaurus terpanjang dengan leher dan ekor super panjang.",
            period = "Periode Jurassic",
            imageRes = R.drawable.diplodocus,
            type = "Herbivora",
            wikiUrl = "https://en.wikipedia.org/wiki/Diplodocus"
        ),
        Dino(
            name = "Parasaurolophus",
            description = """
                Parasaurolophus adalah dinosaurus herbivora dari akhir Kapur yang dikenal karena jambul panjang berongga di kepalanya. Jambul tersebut kemungkinan digunakan untuk menghasilkan suara, menarik pasangan, atau membantu mengatur suhu tubuh.

                Ia berjalan dengan dua atau empat kaki dan hidup dalam kawanan. Panjang tubuhnya sekitar 10 meter dan dikenal sebagai bagian dari kelompok hadrosaur atau dinosaurus bebek.
            """.trimIndent(),
            shortDesc = "Herbivora dengan jambul panjang berongga yang khas di kepalanya.",
            period = "Akhir Periode Kapur",
            imageRes = R.drawable.parasaurolophus,
            type = "Herbivora",
            wikiUrl = "https://en.wikipedia.org/wiki/Parasaurolophus"
        )
    )

    fun getDinoList(): List<Dino> = dinos

    fun getDinoByName(name: String): Dino? =
        dinos.find { it.name.equals(name, ignoreCase = true) }

    fun getDinoFlow(): Flow<List<Dino>> = flowOf(dinos)

    override fun getAllDinos(): Flow<List<Dino>> = flowOf(dinos)

    override fun searchDinos(query: String): Flow<List<Dino>> =
        flowOf(dinos.filter { it.name.contains(query, ignoreCase = true) })
}