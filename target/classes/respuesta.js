var mediaRecorder;
var chunks = [];

var video = document.querySelector('video');
var camara = document.getElementById("camara")
var iniciar = document.getElementById("iniciar");
var grabar = document.getElementById("grabar")
var parar = document.getElementById("parar")

grabar.addEventListener('click', GRABAR)
parar.addEventListener('click', PARAR)

iniciar.addEventListener("click", function(){    
    //Aqui es donde se manda abrir la camará
    navigator.mediaDevices.getUserMedia({
        audio: false,
        video: true
    }).then((stream) => {
        console.log(stream);
        console.log(video);
        console.log(camara);
        mediaRecorder = new MediaRecorder(stream, {mimeType: 'video/webm; codecs=vp8'});
        video.src = "1.mp4";
        camara.srcObject = stream;
        camara.onloadedmetadata = (e) => { camara.play() }
        mediaRecorder.onstop = () => {
            console.log("ya se grabó algo!")
            var clipName = prompt('Enter a name for your sound clip');
    
            var clipContene = document.createElement('article');
            var clipLabel = document.createElement('p');
            var audio = document.createElement('video');
            var dltButton = document.createElement('button');
            audio.width = "250";
      
            clipContene.classList.add('clip');
            audio.setAttribute('controls', '');
            dltButton.innerHTML = "Delete";
            clipLabel.innerHTML = clipName;
      
            soundClips = document.getElementById("xxx")
            clipContene.appendChild(audio);
            clipContene.appendChild(clipLabel);
            
            soundClips.appendChild(clipContene);
      
            audio.controls = true;

            var blob = new Blob(chunks, {type: 'video/webm; codecs=vp8'});
            var audioURL = URL.createObjectURL(blob);
            audio.src = audioURL;
        }
    }).catch(function (error) {
        console.log(error)
    });

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
}

function PARAR(params) {
    mediaRecorder.stop();
    console.log(mediaRecorder.state);
    console.log("recorder stopped");
    mediaRecorder.ondataavailable = function(e) {
        console.log(e)
        chunks.push(e.data);
      }
}

function preguntas(u) {
    let listPreguntas = document.getElementById("preguntas")
    let preguntas = "";
    for (let i = 0; i < u.length; i++) {
        let responder= document.createElement("input");
        let pregunta = document.createElement("li");
        pregunta.textContent = u[i].pregunta + " ";
        responder.className = 'border-2 text-center'
        responder.type = "text";

        listPreguntas.appendChild(pregunta);
    }
};

