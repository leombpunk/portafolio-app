import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { WalkietalkieService } from 'src/app/services/walkietalkie.service';
import { ViewportScroller } from '@angular/common';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output() onLogOutEvent: EventEmitter<any> = new EventEmitter();
  @Input() isLogged: boolean = false;
  nombreApellido: string = "";

  constructor(
    private comunicationService: WalkietalkieService,
    private viewportScroller: ViewportScroller
  ) { }

  ngOnInit(): void {
    this.comunicationService.informarNombre$.subscribe((result:string) => {
      this.nombreApellido = result;
    });
  }

  onLogOut(){
    //intentar usar un eventemiter
    // console.log("emitiendo");
    this.onLogOutEvent.emit();
    this.isLogged = false;
  }

  onClick(elementId: string): void {
    this.viewportScroller.scrollToAnchor(elementId);
  }
}
