<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateLoadsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('loads', function (Blueprint $table) {
            $table->id('idLoad');
            $table->string('codigoBarras');
            $table->unsignedBigInteger('articulo');
            $table->unsignedBigInteger('usuario');
            $table->unsignedBigInteger('domicilio');

            $table->foreign('articulo')
                    ->references('idArticulo')->on('articulos');

            $table->foreign('usuario')
                    ->references('idUsuario')->on('users');

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
        Schema::dropIfExists('loads');
    }
}
