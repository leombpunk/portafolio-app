import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-alert-danger',
  templateUrl: './alert-danger.component.html',
  styleUrls: ['./alert-danger.component.css']
})
export class AlertDangerComponent implements OnInit {

  @Input() mensajeAlert: string = "";
  @Input() mensajeStatus: string = "";
  @Input() mensajeStatusText: string = "";

  constructor() { }

  ngOnInit(): void { }

}
