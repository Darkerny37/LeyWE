<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
//use Illuminate\Database\Eloquent\SoftDeletes;

class Articulo extends Model
{
    use HasFactory;
    //use SoftDeletes;

    //
    public $timestamps = false;

    // esta linea especifica que el primary key es diferente al de Laravel($id)
    protected $primaryKey = 'idArticulo';
}
