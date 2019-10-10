package sample;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.Socket;

public class Cliente{



    private Socket socket;
    private String host;
    private int porta;
    private BufferedReader entrada;
    private BufferedWriter saida;
    private String nome;

    private String ultimaMensagem;

    public Cliente(String host, int porta) throws IOException {

        this.host = host;
        this.porta = porta;

    }


    public void setNome(String nome) throws IOException{
        this.nome = nome;


        //envia o nome para o servidor

        ultimaMensagem = processa(nome);
    }


    public void conectar() throws IOException{
        socket = new Socket(host,porta);

        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        saida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    }


    public void desconectar()throws IOException{

        saida.close();
        entrada.close();
        socket.close();

    }


    public String getUltimaMensagem() {
        return ultimaMensagem;
    }

    public String processa(String msg) throws IOException {

        if(socket.isConnected()){

            saida.write(msg+"\n");
            saida.flush();

            String resp=entrada.readLine();


            this.ultimaMensagem = resp;

        }else{
            this.ultimaMensagem="Morreu!!!";
        }

        return this.ultimaMensagem;


    }




}
