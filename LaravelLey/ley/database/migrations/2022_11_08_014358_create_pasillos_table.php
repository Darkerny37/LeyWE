<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreatePasillosTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('pasillos', function (Blueprint $table) {
            $table->id('idPasillo');
            $table->string('nombrePasillo',30);
            $table->unsignedBigInteger('reserva');
            $table->unsignedBigInteger('domicilio');

            $table->foreign('reserva')
                    ->references('idReserva')->on('reservas');

            $table->foreign('domicilio')
                    ->references('idDomicilio')->on('domicilios');
            //$table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('pasillos');
    }
}
