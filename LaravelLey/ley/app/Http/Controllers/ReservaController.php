<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Reserva;

class ReservaController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $res = Reserva::where('idReserva', $id)
                ->first();

        if($res != null){
            return response()->json([
                'data' => $res
            ]);
        }else{
            return response()->json([
                'message' => 'No se encontro la reserva'
            ]);
        }
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $res = Reserva::find($id);

        if($res != null){

            $res->nombreReserva = $request->nombreReserva;
            $res->load = $request->load;
            $res->cantidad = $request->cantidad;
            $res->disponible = $request->disponible;

            if($res->save()){
                return response()->json([
                    'message' => 'Se editaron los registros correctamente'
                ]);
            }else{
                return response()->json([
                    'message' => 'No se pudieron editar los campos'
                ]);
            }
        }else{
            return response()->json([
                'message' => 'Campos nulos'
            ]);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
