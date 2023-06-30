import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';


interface ConvertResponse {
  result: string;
}
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls:['app.component.css']

})
export class AppComponent {
  from: string = 'String';
  to: string = 'Base64';
  data: string = '';
  result: string = '';

  constructor(private http: HttpClient) {}

  convertData() {
    const url = `http://localhost:8080/api/${this.from}/${this.to}`;
    this.http.post<ConvertResponse>(url, { data: this.data }).subscribe(
      res => {
        this.result = res.result;
      },
      err => {
        console.error(err);
      }
    );
  }
}
