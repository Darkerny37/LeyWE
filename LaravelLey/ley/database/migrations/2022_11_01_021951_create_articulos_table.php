<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateArticulosTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('articulos', function (Blueprint $table) {
            $table->id('idArticulo');
            $table->string('nombreArticulo');
            $table->string('numeroSerie');
            $table->integer('cantidad');
            //$table->softDeletes();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('articulos');
        // forma para borrar datos sin borrar datos :)
        /*Schema::table('articulos', function(Blueprint $table){
            $table->softDeletes();
        });
        */
    }
}
