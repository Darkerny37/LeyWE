<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Reporte extends Model
{
    use HasFactory;

    public function reservas(){
        return $this->hasMany(Reserva::class, 'idReserva','reservas');
    }

    public function users(){
        return $this->hasMany(User::class, 'idUsuario','users');
    }
}
