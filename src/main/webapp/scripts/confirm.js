/**
 * 
 */

const confirmDelete = (idcon) => {
	
	const conf = confirm("Are you sure you want to delete the contact?")
	
	window.location.href = "delete?idcon=" + idcon
}