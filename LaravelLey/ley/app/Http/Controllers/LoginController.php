<?php

namespace App\Http\Controllers;
use App\Models\User;
use Illuminate\Http\Request;

class LoginController extends Controller
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
        if($request->usuario !==null and $request->passUsuario  !== null)
        {
            //Se crea una variable para obtener nuestro usuario, se obtiene de la base de datos
            $tempUsuario = User::where('usuario', $request->usuario)->first();

            //Si el usuario no es nulo, significa que existe
            if($tempUsuario != null){

                //strcmp nos permite validar dos strings para checar si la contraseña es la misma del usuario que obtivumos
                //trae 0 si son iguales
                if(strcmp($tempUsuario->passUsuario, $request->passUsuario)== 0){
                    return response()->json([
                        'message' => 'El usuario existe',
                    ]);
                }else{
                    return response()->json([
                        'message' => 'La contraseña no coincide',
                    ]);
                }
            }else{
                return response()->json([
                    'message' => 'El usuario no existe',
                ]);
            }

        }else{
            return response()->json([
                'message' => 'Llena los campos',
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
        //
    }
}
