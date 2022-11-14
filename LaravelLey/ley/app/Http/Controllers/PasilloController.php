<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Pasillo;

class PasilloController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

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
        $pas = new Pasillo();

        if( $request->codigoBarras !== null and
            $request->nombrePasillo !== null and
            $request->reserva !== null and
            $request->domicilio !== null)
        {
            $pas->codigoBarras = $request->codigoBarras;
            $pas->nombrePasillo = $request->nombrePasillo;
            $pas->reserva = $request->reserva;
            $pas->domicilio = $request->domicilio;

            if($pas->save())
            {
                return response()->json([
                    'message' => 'Registro guardado exitosamente'
                ]);
            }else{
                return response()->json([
                    'mesagge' => 'No se ha podido guardar el registro'
                ]);
            }
        }else{
            return response()->json([
                'message' => 'Campos nulos'
            ]);
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $pas = Pasillo::where('codigoBarras', '=', $id)
            ->first();

        if($pas != null)
        {
            return response()->json([
                'data' => $pas
            ]);
        }else{
            return response()->json([
                'message' => 'No se encontraro el pasillo'
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
        $pas = Pasillo::find($id);

        if($pas != null){
            $pas->codigoBarras = $request->codigoBarras;
            $pas->nombrePasillo = $request->nombrePasillo;
            $pas->reserva = $request->reserva;
            $pas->domicilio = $request->domicilio;

            if($pas->save()){
                return response()->json([
                    'message' => 'Se editaron los registros correctamente'
                ]);
            }else{
                return response()->json([
                    'message' => 'No se pudieron editar los registros'
                ]);
            }
        }else{
            return response()->json([
                'message' => 'Datos nulos'
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
        $pas = Pasillo::find($id);

        if($pas != null)
        {
            if($pas->delete())
            {
                return response()->json([
                    'message' => 'Se elimino correctamente'
                ]);
            }else{
                return response()->json([
                    'message' => 'No se pudo eliminar datos del pasillo'
                ]);
            }
        }else{
            return response()->json([
                'message' => 'No se encontro el pasillo'
            ]);
        }
    }
}
