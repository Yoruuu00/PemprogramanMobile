package com.example.modul3.data.datasource

import com.example.modul3.R
import com.example.modul3.domain.model.Dinosaur

object LocalDinosaurDataSource {
    fun getDinosaurs(): List<Dinosaur> {
        return listOf(
            Dinosaur(
                "Tyrannosaurus Rex",
                """
                Tyrannosaurus Rex adalah predator terbesar di zamannya...
                """.trimIndent(),
                "Predator terbesar di zamannya dengan rahang yang sangat kuat.",
                "Akhir Periode Kapur",
                R.drawable.tyrannosaurusrex,
                "Karnivora",
                "https://en.wikipedia.org/wiki/Tyrannosaurus"
            ),
            Dinosaur(
                "Triceratops",
                """
                Triceratops adalah dinosaurus herbivora besar...
                """.trimIndent(),
                "Herbivora besar dengan tiga tanduk ikonik di wajahnya.",
                "Akhir Periode Kapur",
                R.drawable.triceratops,
                "Herbivora",
                "https://en.wikipedia.org/wiki/Triceratops"
            ),
            // ... Lanjutkan untuk semua dinosaurus lainnya seperti kode di jawaban sebelumnya ...
            // Pastikan semua 10 dinosaurus memiliki data shortDesc dan period.
            // (Saya persingkat agar tidak terlalu panjang, gunakan kode lengkap dari jawaban sebelumnya)
            Dinosaur(
                "Velociraptor",
                """Velociraptor adalah dinosaurus kecil...""".trimIndent(),
                "Karnivora kecil yang terkenal karena kecepatan dan kecerdasannya.",
                "75-71 Juta Tahun Lalu",
                R.drawable.velociraptor, "Karnivora", "https://en.wikipedia.org/wiki/Velociraptor"
            ),
            Dinosaur(
                "Stegosaurus",
                """Stegosaurus adalah dinosaurus herbivora...""".trimIndent(),
                "Herbivora dengan piring tulang besar di punggung dan ekor berduri.",
                "Periode Jurassic Akhir",
                R.drawable.stegosaurus, "Herbivora", "https://en.wikipedia.org/wiki/Stegosaurus"
            ),
            Dinosaur(
                "Brachiosaurus",
                """Brachiosaurus adalah salah satu sauropoda terbesar...""".trimIndent(),
                "Sauropoda raksasa dengan leher sangat panjang dan kaki depan yang tinggi.",
                "Periode Jurassic",
                R.drawable.brachiosaurus, "Herbivora", "https://en.wikipedia.org/wiki/Brachiosaurus"
            ),
            Dinosaur(
                "Spinosaurus",
                """Spinosaurus adalah dinosaurus karnivora terbesar...""".trimIndent(),
                "Karnivora semi-akuatik dengan layar punggung yang khas.",
                "112-93 Juta Tahun Lalu",
                R.drawable.spinosaurus, "Karnivora", "https://en.wikipedia.org/wiki/Spinosaurus"
            ),
            Dinosaur(
                "Ankylosaurus",
                """Ankylosaurus adalah dinosaurus herbivora...""".trimIndent(),
                "Herbivora berlapis zirah dengan ekor besar berbentuk gada.",
                "Akhir Periode Kapur",
                R.drawable.ankylosaurus, "Herbivora", "https://en.wikipedia.org/wiki/Ankylosaurus"
            ),
            Dinosaur(
                "Allosaurus",
                """Allosaurus adalah predator utama...""".trimIndent(),
                "Predator puncak dari periode Jurassic, sering disebut 'singa Jurassic'.",
                "Periode Jurassic Akhir",
                R.drawable.allosaurus, "Karnivora", "https://en.wikipedia.org/wiki/Allosaurus"
            ),
            Dinosaur(
                "Diplodocus",
                """Diplodocus adalah sauropoda raksasa...""".trimIndent(),
                "Salah satu dinosaurus terpanjang dengan leher dan ekor super panjang.",
                "Periode Jurassic",
                R.drawable.diplodocus, "Herbivora", "https://en.wikipedia.org/wiki/Diplodocus"
            ),
            Dinosaur(
                "Parasaurolophus",
                """Parasaurolophus adalah dinosaurus herbivora...""".trimIndent(),
                "Herbivora dengan jambul panjang berongga yang khas di kepalanya.",
                "Akhir Periode Kapur",
                R.drawable.parasaurolophus, "Herbivora", "https://en.wikipedia.org/wiki/Parasaurolophus"
            )
        )
    }
}