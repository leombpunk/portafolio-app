import { Component, OnInit, Input } from '@angular/core';
import { Skill } from 'src/app/models/skills';

@Component({
  selector: 'app-card-skills',
  templateUrl: './card-skills.component.html',
  styleUrls: ['./card-skills.component.css']
})
export class CardSkillsComponent implements OnInit {
  
  @Input() titleSkill: string = "";
  @Input() nivelSkill: number = 0;
  @Input() tipoSkill: string = "";
  @Input() skillData: Skill = new Skill();
  @Input() isLogged: boolean = false;

  constructor() { }

  ngOnInit(): void { }
}
