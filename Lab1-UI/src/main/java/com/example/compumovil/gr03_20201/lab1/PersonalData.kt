package com.example.compumovil.gr03_20201.lab1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_personal_data.*
import java.util.*

class PersonalData : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var spinner: Spinner? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    private var itemList = arrayOf(

        "Primaria",
        "Secundaria",
        "Universidad",
        "Otro"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        spinner = findViewById(R.id.spinner1)
        arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, itemList)
        spinner?.adapter = arrayAdapter
        spinner?.onItemSelectedListener = this

        btnSiguiente.setOnClickListener {

            if (editTextTextPersonName.text.toString() != "" && editTextTextPersonName2.text.toString() != "" && txtCalendar.text.toString() != "") {

                val intent: Intent = Intent(this, DatosPersonales::class.java)
                var Nombre:String = editTextTextPersonName.text.toString()
                var Apellido:String = editTextTextPersonName2.text.toString()
                radioGroup.setOnCheckedChangeListener { group, checkedId ->
                    if (checkedId==R.id.radioSexMas){
                        var Sexo:String = radioSexMas.text.toString()
                        intent.putExtra("Sexo",Sexo)
                    }
                    if (checkedId==R.id.radioSexFem){
                        var Sexo:String = radioSexFem.text.toString()
                        intent.putExtra("Sexo",Sexo)
                    }
                }


                var fecha:String = txtCalendar.text.toString()
                var Escolari:String = spinner1.selectedItem.toString()
                intent.putExtra("Nombre", Nombre)
                intent.putExtra("Apellido", Apellido)

                intent.putExtra("Fecha",fecha)
                intent.putExtra("Escolaridad", Escolari)
                startActivity(intent)
            } else {
                Toast.makeText(this, "ingrese campos obligatorios", Toast.LENGTH_LONG).show()
            }

        }

    }

    fun validatePerson(view: View) {

    }


    @SuppressLint("SetTextI18n")
    fun funDate (view: View){
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(this, android.R.style.Theme_Holo_Dialog, DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
            txtCalendar.text ="$dayOfMonth, $monthOfYear $year"
        }, year, month, day)

        //show datepicker
        dpd.show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(applicationContext, "Nada Seleccionado", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}
