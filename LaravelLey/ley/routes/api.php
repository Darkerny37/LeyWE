<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

/*login*/
Route::post('/login', [App\Http\Controllers\LoginController::class, 'store']);


/*Articulo*/
Route::post('/ArticuloAgregar', [App\Http\Controllers\ArticuloController::class, 'store']);

Route::delete('/ArticuloEliminar/{id}', [App\Http\Controllers\ArticuloController::class, 'destroy']);

Route::get('/ArticuloConsultar/{id}', [App\Http\Controllers\ArticuloController::class, 'show']);
/*fin*/

/*Pasillo*/
Route::post('/PasilloAgregar', [App\Http\Controllers\PasilloController::class, 'store']);

Route::delete('/PasilloEliminar/{id}', [App\Http\Controllers\PasilloController::class, 'destroy']);

Route::get('/PasilloConsultar/{id}', [App\Http\Controllers\PasilloController::class, 'show']);
/*fin*/

Route::get('/ReporteConsultar/{id}', [App\Http\Controllers\ReporteController::class, 'show']);
