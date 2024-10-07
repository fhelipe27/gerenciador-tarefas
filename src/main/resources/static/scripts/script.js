function validarDatas() {
    var dataInicio = document.getElementById("dataInicio").value;
    var dataFinal = document.getElementById("dataFinal").value;

    if (new Date(dataFinal) < new Date(dataInicio)) {
        alert("A data final deve ser igual ou maior que a data de início.");
        return false;
    }
    return true;
}
