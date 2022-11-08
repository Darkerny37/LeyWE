<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateReportesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('reportes', function (Blueprint $table) {
            $table->id('idReporte');
            $table->string('descripcion',200);
            $table->unsignedBigInteger('reserva');
            $table->integer('cantidad');
            $table->unsignedBigInteger('usuario');

            $table->foreign('reserva')
                    ->references('idReserva')->on('reservas');

            $table->foreign('usuario')
                    ->references('idUsuario')->on('users');
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
        Schema::dropIfExists('reportes');
    }
}
