<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateReservasTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('reservas', function (Blueprint $table) {
            $table->id('idReserva');
            $table->string('nombreReserva',30);
            $table->unsignedBigInteger('articulo');
            $table->integer('cantidad');
            $table->boolean('disponible');

            $table->foreign('articulo')
                    ->references('idArticulo')->on('articulos');

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
        Schema::dropIfExists('reservas');
    }
}
