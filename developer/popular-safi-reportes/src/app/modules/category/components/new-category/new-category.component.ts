import { Component, Inject, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CategoryService } from 'src/app/modules/shared/services/category.service';

@Component({
  selector: 'app-new-category',
  templateUrl: './new-category.component.html',
  styleUrls: ['./new-category.component.css']
})
export class NewCategoryComponent implements OnInit{

  public categoryForm!: FormGroup;
  private fb = inject(FormBuilder);
  private categoryService= inject(CategoryService);
  private dialogRef= inject(MatDialogRef);
  public data = inject(MAT_DIALOG_DATA);
  estadoFormulario: string = "";

  ngOnInit(): void {

    console.log(this.data);
    this.estadoFormulario = "Agregar";
    
    this.categoryForm = this.fb.group({
      codigo: ['', Validators.required],
      moneda: ['', Validators.required],
      nroCuota: ['', Validators.required],
      fechaDesembolso: ['', Validators.required],
      fechaPago: ['', Validators.required],
      monto: ['', Validators.required],
      tea: ['', Validators.required],
      interesCompensatorio: ['', Validators.required],
      interesProvision: ['', Validators.required],
      igv: ['', Validators.required],
      diasTranscurridos: ['', Validators.required],
      total: ['', Validators.required]

    })

    if (this.data != null ){
      this.updateForm(this.data);
      this.estadoFormulario = "Actualizar";
    }
  }

  onSave(){

    let data = {
      codigo: this.categoryForm.get('codigo')?.value,
      moneda: this.categoryForm.get('moneda')?.value,
      nroCuota: this.categoryForm.get('nroCuota')?.value,
      fechaDesembolso: this.categoryForm.get('fechaDesembolso')?.value,
      fechaPago: this.categoryForm.get('fechaPago')?.value,
      monto: this.categoryForm.get('monto')?.value,
      tea: this.categoryForm.get('tea')?.value,
      interesCompensatorio: this.categoryForm.get('interesCompensatorio')?.value,
      interesProvision: this.categoryForm.get('interesProvision')?.value,
      igv: this.categoryForm.get('igv')?.value,
      diasTranscurridos: this.categoryForm.get('diasTranscurridos')?.value,
      total: this.categoryForm.get('total')?.value
    }

    if (this.data != null ){
      //update registry
      this.categoryService.updateCategorie(data, this.data.id)
              .subscribe( (data: any) =>{
                this.dialogRef.close(1);
              }, (error:any) =>{
                this.dialogRef.close(2);
              })
    } else {
      //create new registry
      this.categoryService.saveCategorie(data)
          .subscribe( (data : any) => {
            console.log(data);
            this.dialogRef.close(1);
          }, (error: any) => {
            this.dialogRef.close(2);
          })
    }
  }

  onCancel(){
    this.dialogRef.close(3);
  }

  updateForm(data: any){
    this.categoryForm = this.fb.group( {
      codigo: [data.codigo, Validators.required],
      moneda: [data.moneda, Validators.required],
      nroCuota: [data.nroCuota, Validators.required],
      fechaDesembolso: [data.fechaDesembolso, Validators.required],
      fechaPago: [data.fechaPago, Validators.required],
      monto: [data.monto, Validators.required],
      tea: [data.tea, Validators.required],
      interesCompensatorio: [data.interesCompensatorio, Validators.required],
      interesProvision: [data.interesProvision, Validators.required],
      igv: [data.igv, Validators.required],
      diasTranscurridos: [data.diasTranscurridos, Validators.required],
      total: [data.total, Validators.required]
    });

  }


}
