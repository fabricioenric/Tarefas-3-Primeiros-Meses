/**
 * Permite apenas caracteres numéricos serem digitados
 * nos campos matrícula e ano do formulário
 */
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57)){
        	return false;
        }
        return true;
}

/**
 * Permite apenas caracteres não numéricos serem digitados
 * no campo nome do formulário
 */
function isLetterKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && charCode != 32 && (charCode < 65) || (charCode > 90 && charCode < 97) || charCode > 122){
        	return false;
        }
        return true;
}