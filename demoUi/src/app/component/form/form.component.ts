import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {FormService} from "../../service/form.service";
import {NotificationService} from "../../service/notification.service";
import {Sector} from "../../domain/sector";
import {Form} from "../../domain/form";
import {SectorService} from "../../service/sector.service";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.less']
})
export class FormComponent implements OnInit, OnDestroy {

  userForm : FormGroup;
  sectors: Sector[];
  form: Form;

  constructor(private formService: FormService,
              private sectorService: SectorService,
              private readonly notificationService: NotificationService,
              private readonly formBuilder: FormBuilder) {
    this.form = new Form;
    this.userFormBuilder();
  }

  ngOnInit() {
    this.getSectors();
    const lastData = sessionStorage.getItem('data');
    if(lastData) {
      this.form.id = JSON.parse(lastData);
      this.getById();
    } else {
      this.getLastRecord();
    }
  }

  userFormBuilder() {
    this.userForm = this.formBuilder.group(
      {
        'id' : new FormControl(this.form.id),
        'name' : new FormControl(this.form.name,[Validators.minLength(1), Validators.maxLength(200), Validators.required]),
        'sectors' : new FormControl(this.form.sectors,Validators.required),
        'agreeTerms' : new FormControl(this.form.agreeTerms,Validators.required)
      }
    );
  }

  save() {
    this.userForm.get("sectors").patchValue(this.checkAndReturnSectorValues());
    if (this.userForm.getRawValue().id) {
      this.formService.update(this.userForm.getRawValue()).subscribe((data) => {
          if(data){
            this.notificationService.success("Update Completed","Update");
            this.userForm.patchValue(data);
            this.form.sectors = this.returnSectorsId(this.form.sectors);
            sessionStorage.setItem('data', JSON.stringify(data.id));
          }
        },
        (error) =>{
          this.notificationService.error("Update Failed " + error.toString(),"Failed")
        }
      );
    }
    else {
      this.formService.save(this.userForm.getRawValue()).subscribe(data => {
          if (data) {
            this.notificationService.success("Saved Completed","Saved");
            this.userForm.patchValue(data);
            this.form.sectors = this.returnSectorsId(this.form.sectors);
            sessionStorage.setItem('data', JSON.stringify(data.id));
          }
        },
        (error) => {
          this.notificationService.error("Saved Failed " + error.toString(),"Failed")
        }
      );
    }
  }

  clear () {
    this.notificationService.clear();
    this.form = new Form();
    this.userForm.reset();
    sessionStorage.clear();
    this.notificationService.info("Form and session cleared","Clear")
  }

  getById() {
    if(this.form && this.form.id) {
      this.formService.getRecord(this.form.id).subscribe(data => {
        this.form = data;
        this.userForm.patchValue(data);
        this.form.sectors = this.returnSectorsId(this.form.sectors);
      }, (error) => this.notificationService.warning(error.toString(), "Retrieve failed"));
    }
  }

  getLastRecord() {
      this.formService.getLastRecord().subscribe((data) => {
        if(data && data.id) {
          this.form = data;
          this.userForm.patchValue(data);
          this.form.sectors = this.returnSectorsId(this.form.sectors);
        }
      }, (error) => this.notificationService.warning(error.toString(), "Retrieve failed"));
  }

  getSectors(){
    this.sectorService.getAll().subscribe((data: Sector[]) => {
      let sectorList = [];
      this.sectors = this.getChildren(sectorList, data);
    }, (error) => this.notificationService.warning(error.toString(), "Retrieve failed"));
  }

  getChildren(sectorList, data) {
    data.forEach((value) => {
      sectorList.push(value);
      value.name = this.getLabelWithHierarcy(value);
     if(value.children.length > 0){
       this.getChildren(sectorList, value.children);
      }
    });
    return sectorList.sort((a, b) => a.id.toString().localeCompare(b.id.toString()));
  }

  getLabelWithHierarcy(value) {
    let result ="";
    for ( let i = 1; i < value.id.toString().length; i++) {
      result += '\xa0\xa0\xa0\xa0';
    }
    return result + value.name;
  }

  checkAndReturnSectorValues() {
    return this.form.sectors
      .map( id => this.sectors.find( sector=> sector.id === id));
  }

  returnSectorsId(data) {
    return data
      .map(d => d.id);
  }

  ngOnDestroy(): void {
    sessionStorage.clear();
  }
}
