<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Load extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $primaryKey = 'idLoad';

    public function articulos(){
        return $this->hasMany(Articulo::class,'idArticulo','articulo');
    }

    public function users(){
        return $this->hasMany(User::class,'idUsuario','users');
    }

    public function domicilios(){
        return $this->hasMany(Domicilio::class,'idDomicilio','domicilios');
    }
}
