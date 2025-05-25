<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Beranda - Raja Iblis</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-br from-gray-900 to-black text-white font-sans">

  <div class="min-h-screen flex flex-col items-center justify-center px-4 text-center">
 
    <h1 class="text-5xl font-extrabold text-purple-500 mb-10">
      Selamat Datang, Di Profil Saya
    </h1>


    <div class="bg-gray-800 p-6 rounded-2xl shadow-xl w-full max-w-md mb-8 border border-purple-700">
      <div class="flex justify-between mb-2">
        <span class="text-purple-300 font-semibold">Nama:</span>
        <span><?= $praktikan['nama'] ?></span>
      </div>
      <div class="flex justify-between">
        <span class="text-purple-300 font-semibold">NIM:</span>
        <span><?= $praktikan['nim'] ?></span>
      </div>
    </div>


    <div class="flex gap-4">
      <a href="/" class="px-6 py-3 rounded-full bg-purple-700 hover:bg-purple-900 transition transform hover:scale-105 shadow-lg text-white font-semibold">
        Beranda
      </a>
      <a href="/home/profil" class="px-6 py-3 rounded-full bg-gray-700 hover:bg-gray-900 transition transform hover:scale-105 shadow-lg text-white font-semibold">
        Profil
      </a>
    </div>

    <footer class="mt-10 text-sm text-gray-500">#Raja Iblis.</footer>
  </div>

</body>
</html>
