<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
        <html lang="fr">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>${demande != null ? "Modification demande de Transformation de visa": "Creation demande de
                transformation de
                visa"}</title>

            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form-visa.css">
            <script src="${pageContext.request.contextPath}/assets/js/jquery-4.0.0.min.js"></script>
            <%@ include file="../../../includes/css.jsp" %>
        </head>

        <body>
            <%@ include file="../../../includes/header.jsp" %>
                <main>
                    <div class="title-page">
                        <h2>Photo et signature</h2>
                    </div>
                    <form class="form-wrap" action="${pageContext.request.contextPath}/demande/cam/${id}"
                        method="post" enctype="multipart/form-data">

                        <div class="form-header">
                            <h2>Photo et signature</h2>
                        </div>
                        <div class="form-body">
                            <div class="groupe-infos">
                                <div class="info-card-item">
                                    <div class="section-title">Photo</div>
                                    <div class="field-group">
                                        <div style="display: flex; flex-direction: column; align-items: center; gap: 10px;  border-radius: 5px;">

                                            <div id="cameraContainer" style="display:none;" >
                                                <video id="video" width="100%" autoplay></video>
                                                <br>
                                            </div>
                                            <img id="preview" width="100%" style="display:none;" />
                                            <button type="button" class="btn btn-primary" id="openCameraBtn" onclick="openCamera()"><i class="mdi mdi-camera"></i></button>
                                            <button type="button" class="btn btn-primary" id="takePhotoBtn" onclick="takePhoto()" style="display:none;"><i class="mdi mdi-camera-iris"></i></button>



                                            <canvas id="canvas" style="display:none;"></canvas>
                                            <input type="file" id="photoFile" name="photo" style="display:none;">
                                        </div>
                                    </div>
                                </div>
                                <div class="info-card-item">
                                    <div class="section-title">Signature</div>
                                    <div class="field-group">
                                        <div>
                                            <canvas id="signature-pad" width="500" height="369"
                                                style="border:1px solid #000000;  background-color: #f5f5f5;"></canvas>

                                            <br>
                                            <button type="button" class="btn btn-danger" onclick="clearPad()"><i class="mdi mdi-rotate-left"></i></button>
                                            <button type="button" class="btn btn-primary" onclick="saveSignature()"><i class="mdi mdi-check"></i></button>

                                            <!-- champ caché -->
                                            <input type="file" style="display:none;" name="signature" id="signatureInput">

                                            <br>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="iddemande" value="${id}">
                        <button class="submit-btn" type="submit">Envoyer le dossier</button>
                        <c:if test="${not empty message}">
                            <div class="alert alert-success">
                                ${message}
                                <span class="close-btn" onclick="this.parentElement.style.display='none'">&times;</span>
                            </div>
                        </c:if>

                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">
                                ${error}
                                <span class="close-btn" onclick="this.parentElement.style.display='none'">&times;</span>
                            </div>
                        </c:if>
                    </form>
                </main>

        </body>

        <script>
           let stream;

const openCameraBtn = document.getElementById('openCameraBtn');
const takePhotoBtn = document.getElementById('takePhotoBtn');

const video = document.getElementById('video');
const photoCanvas = document.getElementById('canvas');
const preview = document.getElementById('preview');


// ==========================
// CAMERA
// ==========================

function openCamera() {

    const container = document.getElementById('cameraContainer');

    navigator.mediaDevices.getUserMedia({ video: true })
        .then(s => {

            stream = s;

            video.srcObject = stream;

            container.style.display = 'block';
            preview.style.display = 'none';

            openCameraBtn.style.display = 'none';
            takePhotoBtn.style.display = 'inline-block';
        })
        .catch(err => {
            console.error("Erreur caméra :", err);
        });
}

function takePhoto() {

    photoCanvas.width = video.videoWidth;
    photoCanvas.height = video.videoHeight;

    const ctx = photoCanvas.getContext('2d');

    ctx.drawImage(video, 0, 0);

    // stop caméra
    stream.getTracks().forEach(track => track.stop());

    document.getElementById('cameraContainer').style.display = 'none';

    openCameraBtn.style.display = 'inline-block';
    takePhotoBtn.style.display = 'none';

    // preview image
    const dataURL = photoCanvas.toDataURL('image/png');

    preview.src = dataURL;
    preview.style.display = 'block';

    // convertir canvas -> file
    photoCanvas.toBlob(function(blob) {

        const file = new File(
            [blob],
            "photo.png",
            { type: "image/png" }
        );

        const dataTransfer = new DataTransfer();

        dataTransfer.items.add(file);

        document.getElementById('photoFile').files = dataTransfer.files;

    }, 'image/png');
}



// ==========================
// SIGNATURE PAD
// ==========================

const signatureCanvas = document.getElementById("signature-pad");

const signatureCtx = signatureCanvas.getContext("2d");

let drawing = false;


// souris appuyée
signatureCanvas.addEventListener("mousedown", (e) => {

    drawing = true;

    signatureCtx.beginPath();

    signatureCtx.moveTo(e.offsetX, e.offsetY);
});


// souris relachée
signatureCanvas.addEventListener("mouseup", () => {

    drawing = false;

    signatureCtx.beginPath();
});


// souris sort du canvas
signatureCanvas.addEventListener("mouseleave", () => {

    drawing = false;

    signatureCtx.beginPath();
});


// mouvement souris
signatureCanvas.addEventListener("mousemove", draw);


function draw(e) {

    if (!drawing) return;

    signatureCtx.lineWidth = 2;
    signatureCtx.lineCap = "round";

    signatureCtx.lineTo(e.offsetX, e.offsetY);

    signatureCtx.stroke();
}


// clear signature
function clearPad() {

    signatureCtx.clearRect(
        0,
        0,
        signatureCanvas.width,
        signatureCanvas.height
    );
}


// save signature manuellement
function saveSignature() {

    generateSignatureFile();
}


// ==========================
// GENERATE SIGNATURE FILE
// ==========================

function generateSignatureFile(callback = null) {

    signatureCanvas.toBlob(function(blob) {

        const file = new File(
            [blob],
            "signature.png",
            { type: "image/png" }
        );

        const dataTransfer = new DataTransfer();

        dataTransfer.items.add(file);

        document.getElementById("signatureInput").files =
            dataTransfer.files;

        if (callback) {
            callback();
        }

    }, "image/png");
}



// ==========================
// AUTO SAVE SIGNATURE
// BEFORE FORM SUBMIT
// ==========================

document.querySelector("form")
    .addEventListener("submit", function(e) {

        e.preventDefault();

        generateSignatureFile(() => {

            this.submit();

        });
    });
        </script>


        </html>