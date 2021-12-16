var idpregunta;
var valor = 0;
var TmpPath;

var txtTarea = document.getElementById("pregunta");
var listTareas = document.getElementById("preguntas");
var btnAgregarRespuestas = document.getElementById("agregaRespuesta");
var txtRespuesta = document.getElementById("respuesta");
var btnAgregar = document.getElementById("agregar");
var video = document.getElementById("video");

btnAgregar.addEventListener("click", agregar);
valor = document.getElementById("agregaValor").value;

$(document).ready(function() {
    $(document).on('change', 'input[type=file]', function(e) {
        TmpPath = URL.createObjectURL(e.target.files[0]);
        $('span').html(TmpPath);
        console.log(TmpPath);
        video.src=TmpPath;      
    });
});

function agregar() {    
    let tarea = document.createElement('li');
    let btnResp = document.createElement('button');
    var btnValor = document.getElementById('agregaValor');

    tarea.className = 'my-2 border-2 w-1/2 text-center'
    tarea.textContent = txtTarea.value;
    btnResp.innerText = 'Agregar respuestas';
    btnResp.className = 'border-2 w-1/2';

    listTareas.appendChild(video);
    listTareas.appendChild(tarea);
    //listTareas.appendChild(btnResp);

    //Respuesta de opción multiple
    btnAgregarRespuestas.addEventListener("click", function(){
        let resp = document.createElement("label");
        resp.textContent = txtRespuesta.value;
        
        //Agrega la respuesta a la base de datos
        axios.post("http://localhost:4567/respuesta", {
        respuesta : document.getElementById("respuesta").value,
        valor : valor,
        idpregunta : idpregunta
        
        .then(function (response) {
            alert("mensaje: pregunta creada "+response.data.status+" con id: "+response.data.id);
            id = response.data.id;
            estado=response.data.status;
        })
        .catch(function (error) {
            console.log(error);            
        })
        
        txtRespuesta.value = null;
        let salto = document.createElement("br");
        listTareas.appendChild(resp);
        listTareas.appendChild(salto);
        resp = null;
        console.log("dentro de función" + resp);
        valor = 0;
    });

    //Agregar la respuesta
    overlay = document.getElementById('overlay'),
	popup = document.getElementById('popup'),
	btnCerrarPopup = document.getElementById('btn-cerrar-popup');

    botonRespuestas.addEventListener('click', function(){
        overlay.classList.add('active');
        popup.classList.add('active');
    });

    btnCerrarPopup.addEventListener('click', function(e){
        e.preventDefault();
        overlay.classList.remove('active');
        popup.classList.remove('active');
    })
    console.log("dentro de función" + tarea);
}

//Enviar preguntas a la base de datos
btnAgregar.addEventListener("click", function(){
    axios.post("http://localhost:4567/pregunta", {
        pregunta : document.getElementById("pregunta").value,
        video : TmpPath 
    }).then(function (response) {
        alert("mensaje: pregunta creada "+response.data.status+" con id: "+response.data.id);
        id = response.data.id;
        idpregunta = id;  
        estado = response.data.status;
    })
    .catch(function (error) {
        console.log(error);
    })
});
console.log("fuera de función:" + tarea);
