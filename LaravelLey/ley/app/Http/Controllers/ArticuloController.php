<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Articulo;

class ArticuloController extends Controller
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
        // art es un objeto de la base de datos
        $art = new Articulo();

        // si es diferente de nulo tiene valores
        if( $request->nombreArticulo !==null and
            $request->numeroSerie !==null and
            $request->cantidad !==null)
        {
            // los atributos de art deben ser iguales a los de la bd
            // se asignan valores del request
            $art->nombreArticulo = $request->nombreArticulo;
            $art->numeroSerie = $request->numeroSerie;
            $art->cantidad = $request->cantidad;

            // de esta forma los cambios quedan guardados
            if($art->save())
            {
                return response()->json([
                    'message' => 'Registro guardado correctamente'
                ]);
            }else{
                return response()->json([
                    'message' => 'No se guardo el registro'
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
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $art = Articulo::findOrFail($id);


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
        //Articulo modelo de la base de datos
        $art = Articulo::find($id);

        if($art != null)
        {
            if($art->delete())
            {
                return response()->json([
                    'message' => 'Se elimino correctamente'
                ]);
            }else{
                return response()->json([
                    'message' => 'No se elimino articulo'
                ]);
            }
        }else{
            return response()->json([
                'message' => 'No se encontro articulo'
            ]);
        }
    }
}
