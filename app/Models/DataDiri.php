<?php

namespace App\Models;

use CodeIgniter\Model;

class DataDiri extends Model
{
    public function getData()
    {
        return [
            'nama' => 'Muhammad Rizki Saputra',
            'nim' => '2310817310014',
            'prodi' => 'Teknologi Informasi',
            'hobi' => 'Mendengar Musik',
            'skill' => 'Bermain game, Nonton F1',
            'gambar' => 'foto.jpg',
            'genre musik' => 'Pop, Soul dan R&B',
            'game favorit' => 'Ghost Of Tsushima, Beyond Two Soul, Red Dead Redemption'
        ];
    }
}
