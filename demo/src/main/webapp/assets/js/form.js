$(document).ready(function () {
  $(".demandeurs").on("change", function () {
    const demandeurSelected = $(this).val();
    console.log(demandeurSelected, " demandeur selectionner");
    const divFolder = $(".folder");
    const form = $(".form-wrap")
    let demandeurAPI = null;
    
    if (demandeurSelected == "") {
        divFolder.show();
        $('.id-demandeur-input').remove()
        $('.types-visas').val('TYPV000001').change();
        $('.situationFamiliale').val(null).change();
        $('.nom').val(null)
        $('.prenom').val(null)
        $('.dtn').val(null)
        $('.profession').val(null)
        $('.nationalite').val(null)
        $('.adressemada').val(null)
        $('.email').val(null)
        $('.tel').val(null)
        $('.passport').val(null)
        $('.datedelivrance').val(null)
        $('.dateexpiration').val(null)
        $('.reference').val(null)
        $('.lieuentree').val(null)
        $('.dateentreemada').val(null)
        $('.dateexpirationvisa').val(null)
        
    }else {
        divFolder.hide();
        const input = $('<input>', {
            type: 'hidden',
            value: demandeurSelected,
            class: 'id-demandeur-input',
            name: 'demandeur.id'
        })
        form.append(input)
        
        $.get('/demande/api/demandeur?id=DMDR000002').then(function(response){
            demandeurAPI = response
            console.log(demandeurAPI);
            
            let typeVisa = demandeurAPI.typeVisa.id == null ? null : demandeurAPI.typeVisa.id;
            let situationFamiliale = demandeurAPI.demandeur.idsituationdefamille == null ? null : demandeurAPI.demandeur.idsituationdefamille;
            let nomDemandeur = demandeurAPI.demandeur.nom == null ? null : demandeurAPI.demandeur.nom;
            let prenomDemandeur = demandeurAPI.demandeur.prenom == null ? null : demandeurAPI.demandeur.prenom;
            let dtn = demandeurAPI.demandeur.dtn == null ? null : demandeurAPI.demandeur.dtn;
            let profession = demandeurAPI.demandeur.profession == null ? null : demandeurAPI.demandeur.profession;
            let idnationalite = demandeurAPI.demandeur.idnationalite == null ? null : demandeurAPI.demandeur.idnationalite;
            let adressemada = demandeurAPI.demandeur.adressemada == null ? null : demandeurAPI.demandeur.adressemada;
            let email = demandeurAPI.demandeur.email == null ? null : demandeurAPI.demandeur.email;
            let tel = demandeurAPI.demandeur.tel == null ? null : demandeurAPI.demandeur.tel;
            let numero = demandeurAPI.passport.numero == null ? null : demandeurAPI.passport.numero;
            let datedelivrance = demandeurAPI.passport.datedelivrance == null ? null : demandeurAPI.passport.datedelivrance;
            let dateexpiration = demandeurAPI.passport.dateexpiration == null ? null : demandeurAPI.passport.dateexpiration;
            let reference = demandeurAPI.visatransformable.reference == null ? null : demandeurAPI.visatransformable.reference;
            let lieuentree = demandeurAPI.visatransformable.lieuentree == null ? null : demandeurAPI.visatransformable.lieuentree;
            let dateentreemada = demandeurAPI.visatransformable.dateentreemada == null ? null : demandeurAPI.visatransformable.dateentreemada;
            let dateexpirationvisa = demandeurAPI.visatransformable.dateexpiration == null ? null : demandeurAPI.visatransformable.dateexpiration;
            
            $('.types-visas').val(typeVisa).change();
            $('.situationFamiliale').val(situationFamiliale).change();
            $('.nom').val(nomDemandeur).change();
            $('.prenom').val(prenomDemandeur).change();
            $('.dtn').val(dtn).change();
            $('.profession').val(profession).change();
            $('.nationalite').val(idnationalite).change();
            $('.adressemada').val(adressemada).change();
            $('.email').val(email).change();
            $('.tel').val(tel).change();
            $('.passport').val(numero).change();
            $('.datedelivrance').val(datedelivrance).change();
            $('.dateexpiration').val(dateexpiration).change();
            $('.reference').val(reference).change();
            $('.lieuentree').val(lieuentree).change();
            $('.dateentreemada').val(dateentreemada).change();
            $('.dateexpirationvisa').val(dateexpirationvisa).change();
        })
    }

  });
});
