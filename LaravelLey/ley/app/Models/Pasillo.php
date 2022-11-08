<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Pasillo extends Model
{
    use HasFactory;

    public function reservas(){
        return $this->hasMany(Reserva::class, 'idReserva','reservas');
    }

    public function domicilios(){
        return $this->hasMany(Domicilio::class, 'idDomicilio','domicilios');
    }
}
