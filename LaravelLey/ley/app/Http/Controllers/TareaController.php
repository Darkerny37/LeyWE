<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Tarea;

class TareaController extends Controller
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
        $tar = new Tarea();

        if( $request->descripcion !==null and
            $request->load !==null)
        {
            $tar->descripcion = $request->descripcion;
            $tar->load = $request->load;

            if($tar->save())
            {
                return response()->json([
                    'message' => 'Se creo la tarea correctamente'
                ]);
            }else{
                return response()->json([
                    'message' => 'No se pudo crear la tarea'
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
        $tar = Tarea::find($id);

        if($tar != null)
        {
            if($tar->delete())
            {
                return response()->json([
                    'message' => 'Se elimino la tarea correctamente'
                ]);
            }else{
                return response()->json([
                    'message' => 'No se pudo eliminar la tarea'
                ]);
            }
        }else{
            return response()->json([
                'message' => 'No se encontro la tarea'
            ]);
        }
    }
}
