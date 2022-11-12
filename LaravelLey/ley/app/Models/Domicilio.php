<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Domicilio extends Model
{
    use HasFactory;

    public $timestamps = false;
    protected $primaryKey = 'idDomicilio';

    public function articulos(){
        return $this->hasMany(Articulo::class,'idArticulo','articulo');
    }
}
