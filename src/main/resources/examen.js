var grabar = document.getElementById("grabar")
grabar.addEventListener('click', GRABAR)
var parar = document.getElementById("parar")
parar.addEventListener('click', PARAR)

var mediaRecorder
var chunks = [];

var video = document.querySelector('video');
var camara = document.getElementById("camara")

var iniciar = document.getElementById("iniciar");




iniciar.addEventListener("click", function(){

    

    
    //Función para poder abrir la camara
    navigator.mediaDevices.getUserMedia({
        audio: false,
        video: true
    }).then((stream) => {
        console.log(stream)
        mediaRecorder = new MediaRecorder(stream, {mimeType: 'video/webm; codecs=vp8'});
    
        // alert('gracias por permitirnos verte')
        
        console.log(video)
        console.log(camara)
        video.src = "1.mp4"
        camara.srcObject = stream;
        camara.onloadedmetadata = (e) => { camara.play() }
        mediaRecorder.onstop = () => {
            console.log("ya se grabó algo!")
            var clipName = prompt('Enter a name for your sound clip');
    
            var clipContainer = document.createElement('article');
            var clipLabel = document.createElement('p');
            var audio = document.createElement('video');
            var deleteButton = document.createElement('button');
            audio.width = "200";
      
            clipContainer.classList.add('clip');
            audio.setAttribute('controls', '');
            deleteButton.innerHTML = "Delete";
            clipLabel.innerHTML = clipName;
      
            soundClips = document.getElementById("xxx")
            clipContainer.appendChild(audio);
            clipContainer.appendChild(clipLabel);
            
            soundClips.appendChild(clipContainer);
      
            audio.controls = true;
            var blob = new Blob(chunks, {type: 'video/webm; codecs=vp8'});
            // chunks = [];
            var audioURL = URL.createObjectURL(blob);
            audio.src = audioURL;
            
            // esto debí colocarlo en la función PARAR
            // mediaRecorder.ondataavailable = function(e) {
            //     console.log(e)
            //     chunks.push(e.data);
            //   }
        }
    }).catch(function (error) {
        console.log(error)
        // alert('Si gustas utilizar este sistema permite el acceso a tu cámara')
    });

    //axios para obtener el objeto gson
    axios.get("http://localhost:4567/preguntas")
    .then(function (response) {
        console.log(response.data);
        preguntas(response.data);
    })
    .catch(function (error) {
        console.log(error);
    })



    lista.appendChild(preguntaslista);
})



function GRABAR(params) {
    mediaRecorder.start();
    console.log(mediaRecorder.state);
    console.log("recorder started");
    // record.style.background = "red";
    // record.style.color = "black";
}

function PARAR(params) {
    mediaRecorder.stop();
    console.log(mediaRecorder.state);
    console.log("recorder stopped");

    // este método debe ir al parar para pushear la captura en el array
    mediaRecorder.ondataavailable = function(e) {
        console.log(e)
        chunks.push(e.data);
      }
    // record.style.background = "";
    // record.style.color = "";
}

function preguntas(u) {
    let listPreguntas = document.getElementById("preguntas")
    let preguntas = "";
    for (let i = 0; i < u.length; i++) {
        let responder= document.createElement("input");
        let pregunta = document.createElement("li");
        pregunta.textContent = u[i].pregunta + " ";
        responder.type = "text";

        listPreguntas.appendChild(pregunta);
        listPreguntas.appendChild(responder);
    }
}

