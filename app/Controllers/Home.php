<?php

namespace App\Controllers;

use App\Models\DataDiri;
use App\Models\PraktikanModel;

class Home extends BaseController
{
    public function index()
    {
        $model = new DataDiri();
        $data['praktikan'] = $model->getData();
        return view('beranda', $data);
    }

    public function profil()
    {
        $model = new DataDiri();
        $data['praktikan'] = $model->getData();
        return view('profil', $data);
    }
}
