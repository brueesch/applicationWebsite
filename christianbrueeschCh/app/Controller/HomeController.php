<?php
class HomeController extends AppController {
	public $helpers = array('Html', 'Form');

	public function index() {
		 $this->layout= 'home';
	}  

}
?>