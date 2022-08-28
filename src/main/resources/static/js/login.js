
async function iniciarSesion() {

   let datos = {};

   datos.email = document.getElementById("txtEmail").value;
   datos.password = document.getElementById("txtPassword").value;

   const response = await fetch('api/login', {
      method: 'POST',
      headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
   });
   const res = await response.text();
   if ( res != 'FAIL' ) {
      localStorage.token = res;
      localStorage.email = datos.email;
      window.location.href = "index.html";
   } else {
      alert("Credenciales mal ingresadas! Por favor, intente nuevamente");
   }
}

