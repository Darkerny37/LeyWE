<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Tarea extends Model
{
    use HasFactory;

    public $timestamps = false;
    protected $primaryKey = 'idTarea';

    public function loads(){
        return $this->hasMany(Load::class,'idLoad','loads');
    }
}
