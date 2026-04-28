$(document).ready(function () {
  $(".demandeurs").on("change", function () {
    const demandeurSelected = $(this).val();
    console.log(demandeurSelected, " demandeur selectionner");
    const divFolder = $(".folder");
    const form = $(".form-wrap")
    let demandeurAPI = null;
    
    if (demandeurSelected == "") {
        divFolder.show();
        $('.groupe-infos').removeClass('hide')
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

        $('.types-visas').prop('readonly', false);
            $('.situationFamiliale').prop('readonly', false);
            $('.nom').prop('readonly', false);
            $('.prenom').prop('readonly', false);
            $('.dtn').prop('readonly', false);
            $('.profession').prop('readonly', false);
            $('.nationalite').prop('readonly', false);
            $('.adressemada').prop('readonly', false);
            $('.email').prop('readonly', false);
            $('.tel').prop('readonly', false);

            $('.passport').prop('readonly', false);
            $('.datedelivrance').prop('readonly', false);
            $('.dateexpiration').prop('readonly', false);

            $('.reference').prop('readonly', false);
            $('.lieuentree').prop('readonly', false);
            $('.dateentreemada').prop('readonly', false);
            $('.dateexpirationvisa').prop('readonly', false);

            $('input[name="idTypeDemande"]').each(function () {
                if ($(this).val() === 'TYPDMD000001') {
                    $(this).parent().show(); // cache le label complet
                }
            });

            $('.title-passport').text('Passeport');
        
    }else {
        divFolder.hide();
        $('.groupe-infos').addClass('hide')
        // const input = $('<input>', {
        //     type: 'hidden',
        //     value: demandeurSelected,
        //     class: 'id-demandeur-input',
        //     name: 'demandeur.id'
        // })
        // form.append(input)
        
        $.get('/demande/api/demandeur?id='+demandeurSelected).then(function(response){
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


            $('.types-visas').prop('readonly', true);
            $('.situationFamiliale').prop('readonly', true);
            $('.nom').prop('readonly', true);
            $('.prenom').prop('readonly', true);
            $('.dtn').prop('readonly', true);
            $('.profession').prop('readonly', true);
            $('.nationalite').prop('readonly', true);
            $('.adressemada').prop('readonly', true);
            $('.email').prop('readonly', true);
            $('.tel').prop('readonly', true);

            $('.passport').prop('readonly', true);
            $('.datedelivrance').prop('readonly', true);
            $('.dateexpiration').prop('readonly', true);

            $('.reference').prop('readonly', true);
            $('.lieuentree').prop('readonly', true);
            $('.dateentreemada').prop('readonly', true);
            $('.dateexpirationvisa').prop('readonly', true);

            $('input[name="idTypeDemande"]').each(function () {
                if ($(this).val() === 'TYPDMD000001') {
                    $(this).parent().hide(); // cache le label complet
                }
            });

            $('.title-passport').text('Passeport');
        })
    }

  });


  $('input[name="idTypeDemande"]').change(function () {
    let valeur = $('input[name="idTypeDemande"]:checked').val();
    console.log(valeur);
    
    if (valeur == "TYPDMD000002" && $(".demandeurs").val() != "") {
        $('.passport').prop('readonly', true);
        $('.datedelivrance').prop('readonly', true);
        $('.dateexpiration').prop('readonly', true);

        $('.title-passport').text('Passeport');
    }
    if (valeur == "TYPDMD000003" && $(".demandeurs").val() != "") {
        $('.passport').prop('readonly', false);
        $('.datedelivrance').prop('readonly', false);
        $('.dateexpiration').prop('readonly', false);
        $('.title-passport').text('Nouvelle passeport');
    }

    if (valeur == "TYPDMD000001" && $(".demandeurs").val() != "") {
        $('.passport').prop('readonly', true);
        $('.datedelivrance').prop('readonly', true);
        $('.dateexpiration').prop('readonly', true);
        $('.title-passport').text('Passeport');
    }

    if (valeur == "TYPDMD000003" && $(".demandeurs").val() == "") {
        $('.new-passport').show();
    
    }else{
        $('.new-passport').hide();
    }

    if (valeur == "TYPDMD000003" && $(".demandeurs").val() == "" || valeur == "TYPDMD000002" && $(".demandeurs").val() == "") {
        $('.new-visa-carte').show();
    
    }else{
        $('.new-visa-carte').hide();
    }

    
    
});
});
