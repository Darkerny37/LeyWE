<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Load;

class LoadController extends Controller
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
        $load = new Load();

        if( $request->codigoBarras !== null and
            $request->articulo !== null and
            $request->usuario !== null and
            $request->domicilio !== null)
        {
            $load->codigoBarras = $request->codigoBarras;
            $load->articulo = $request->articulo;
            $load->usuario = $request->usuario;
            $load->domicilio = $request->domicilio;

            if($load->save())
            {
                return response()->json([
                    'message' => 'Load guardado correctamente'
                ]);
            }else{
                return response()->json([
                    'message' => 'No se pudo guardar el Load'
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
        $load = Load::where("codigoBarras", $id)
                ->first();

        if($load != null){
            return response()->json([
                'data' => $load
            ]);
        }else{
            return response()->json([
                'message' => 'No se encontraron datos'
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
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $load = Load::find($id);

        if($load != null){
            if($load->delete()){
                return response()->json([
                    'message' => 'Se elimino correctamente'
                ]);
            }else{
                return response()->json('No se pudo eliminar el Load');
            }
        }else{
            return response()->json([
                'message' => 'No se encontro el Load'
            ]);
        }
    }
}
