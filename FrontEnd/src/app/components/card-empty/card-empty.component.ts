import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-card-empty',
  templateUrl: './card-empty.component.html',
  styleUrls: ['./card-empty.component.css']
})
export class CardEmptyComponent implements OnInit {

  @Input() cardHeader: string = "";
  @Input() cardText: string = "";

  constructor() { }

  ngOnInit(): void {
  }

}
