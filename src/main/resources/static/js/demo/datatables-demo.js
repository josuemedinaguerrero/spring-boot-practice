// Call the dataTables jQuery plugin
$(document).ready(function() {
   cargarUsuarios();
   $('#dataTable').DataTable();
});

const getHeaders = () => ({
   'Accept': 'application/json',
   'Content-Type': 'application/json',
   'Authorization': localStorage.token
})

async function cargarUsuarios() {
   const response = await fetch('api/usuarios', {
      method: 'GET',
      headers: getHeaders()
   });
   const res = await response.json();

   let listaHTML = '';
   for ( let { id, nombre, apellido, email, telefono } of res ) {
      telefono = ( telefono == null ) ? '-' : telefono;
      let botonEliminar = '<a href="#" onclick="eliminarUsuario('+id+')" class="btn btn-danger btn-circle btn-sm"><li class="fas fa-trash"></li></a>';
      listaHTML += `<tr><td>${id}</td><td>${nombre} ${apellido}</td><td>${email}</td><td>${telefono}</td><td>${botonEliminar}</td></tr>`;
   }
   document.querySelector('#dataTable tbody').innerHTML = listaHTML;
}

async function eliminarUsuario( id ) {
   if (!confirm("¿Desea eliminar este usuario?")) { return; }
   await fetch(`api/usuarios/${id}`, {
      method: 'DELETE',
      headers: getHeaders()
   });
   location.reload();
}

