<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Profil Raja Iblis</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-b from-black to-gray-900 text-white font-sans">

  <div class="min-h-screen px-4 py-10 flex flex-col items-center">
 
    <h1 class="text-4xl font-extrabold text-purple-500 mb-10 text-center">
      Profil Sang Raja Iblis
    </h1>

    <div class="bg-gray-800 p-8 rounded-2xl shadow-xl w-full max-w-4xl border border-purple-700 flex flex-col md:flex-row-reverse gap-8 items-center md:items-start">
      
      <?php if (!empty($praktikan['gambar'])): ?>
        <img src="/images/SangMahaRaja.jpg" alt="Raja" class="w-48 h-48 object-cover rounded-full border-4 border-purple-600 shadow-lg">
      <?php endif; ?>

      <ul class="space-y-4 text-left text-lg text-gray-300 w-full">
        <li>
          <span class="text-purple-300 font-semibold block">Nama:</span>
          <span class="block"><?= $praktikan['nama'] ?></span>
        </li>
        <li>
          <span class="text-purple-300 font-semibold block">NIM:</span>
          <span class="block"><?= $praktikan['nim'] ?></span>
        </li>
        <li>
          <span class="text-purple-300 font-semibold block">Prodi:</span>
          <span class="block"><?= $praktikan['prodi'] ?></span>
        </li>
        <li>
          <span class="text-purple-300 font-semibold block">Hobi:</span>
          <span class="block"><?= $praktikan['hobi'] ?></span>
        </li>
        <li>
          <span class="text-purple-300 font-semibold block">Skill:</span>
          <span class="block"><?= $praktikan['skill'] ?></span>
        </li>
        <li>
          <span class="text-purple-300 font-semibold block">Genre Musik:</span>
          <span class="block"><?= $praktikan['genre musik'] ?></span>
        </li>
        <li>
          <span class="text-purple-300 font-semibold block">Game Favorit:</span>
          <span class="block"><?= $praktikan['game favorit'] ?></span>
        </li>
      </ul>
    </div>

   
    <div class="mt-10 flex gap-4">
      <a href="/" class="px-6 py-3 rounded-full bg-purple-700 hover:bg-purple-900 transition transform hover:scale-105 shadow-md font-semibold text-white">
        Kembali ke Beranda
      </a>
      <a href="/home/profil" class="px-6 py-3 rounded-full bg-gray-700 hover:bg-gray-900 transition transform hover:scale-105 shadow-md font-semibold text-white">
        Lihat Lagi Profil
      </a>
    </div>
  </div>

</body>
</html>
