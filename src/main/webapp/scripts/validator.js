/**
 *  Form validation
 */

const validate = () => {
	let name = frmContact.cttName.value
	let phone = frmContact.phone.value
	console.log(name)
	console.log(phone)
	
	
	if(name === "") {
		alert("Fill in the name field")
		frmContact.cttName.focus()
		return false
	} else if (phone === "") {
		alert("Fill in the phone field")
		frmContact.phone.focus()
		return false
	} else {
		document.forms["frmContact"].submit()
	}
}