
async function registrarUsuario() {

   let datos = {};

   datos.nombre = document.getElementById("txtNombre").value;
   datos.apellido = document.getElementById("txtApellido").value;
   datos.email = document.getElementById("txtEmail").value;
   datos.password = document.getElementById("txtPassword").value;
   const repetirPassword = document.getElementById("txtRepetirPassword").value;

   if ( datos.password != repetirPassword ) {
      alert("La contraseña que escribiste es diferente");
      return;
   }

   await fetch('api/usuarios', {
      method: 'POST',
      headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
   });
   alert("La cuenta fue creada con éxito!");
   window.location.href = 'index.html';
}
