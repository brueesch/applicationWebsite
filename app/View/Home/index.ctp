<!DOCTYPE html>

<html>
<head>
<title>Home</title>
	<?php
    		$this->Html->css('styles', array('inline' => false));
 
	?>

</head>

<body>

	<nav>
		<ul>
			<li><a href="./home">Home</a></li>
			<li><a href="#pictures">Pictures</a></li>
			<li><a href="#movies">Movies</a></li>
			<li><a href="#programs">Programs</a></li>
			<li><a href="#games">Games</a></li>
			<li><a href="./locations">Locations</a></li>
			<li><a href="#Kontakt">Kontakt</a></li>
		</ul>
	</nav>

	<article>
		<div class="page-wrap">
			<div id="cv">

				<p class="title">Berufliche Erfahrung</p>
				<div class="cvLeft">
					<p>01/2012 – 07/2012:</p>
					<p>08/2008 – 10/2009:</p>
				</div>

				<div class="cvRight">
					<p>Pöyry Schweiz AG, Zürich: Elektroplaner, Sachbearbeiter</p>
					<p>Brüniger & Co. AG, Chur: Elektroplaner, Sachbearbeiter</p>
				</div>

				<div class="clear"></div>

				<p class="title">Ausbildung</p>
				<div class="cvLeft">
					<p>09/2012 – heute:</p>
					<p>08/2010 – 07/2011:</p>
					<p>08/2004 – 07/2008:</p>
				</div>

				<div class="cvRight">
					<p>ZHAW, Winterthur: Informatik, Software Engineering</p>
					<p>Hochschule für Technik und Wirtschaft, Chur: Berufsmaturität</p>
					<p>Brüniger & Co. AG, Chur: Lehre Elektroplaner</p>
				</div>

				<div class="clear"></div>

				<p class="title">Auslandsaufenthalte</p>

				<div class="cvLeft">
					<p>04/2010 – 06/2010:</p>
				</div>

				<div class="cvRight">
					<p>EF Language Center, Miami Beach: Englische
						Sprachschule</p>
				</div>

				<div class="clear"></div>

				<p class="title">Sprachen</p>

				<div class="cvLeftShort">
					<p>Deutsch:</p>
					<p>Englisch:</p>
				</div>
				<div class="cvRight">
					<p>Muttersprache</p>
					<p>schriftlich und mündlich sehr gut</p>
				</div>

			</div>

			<div id="rightContent">
				<div id="profilePicture">
				<?php echo	$this->Html->image('me.JPG', array('alt' => 'Profil Bild'));
				?>
				</div>
				<div id="personalData">
						<p class="pdLeft">Name:</p>
						<p class="pdRight">Brüesch Christian</p>
						<p class="pdLeft">Adresse:</p>
						<p class="pdRight">Rümikerstrasse 32</p>
						<p class="pdRight">8409 Winterthur</p>
						<p class="pdLeft">Telefon:</p>
						<p class="pdRight">+41 (0) 79 211 40 34</p>
						<p class="pdLeft">E-Mail:</p>
						<p class="pdRight">me@christianbrueesch.ch</p>
						<p class="pdLeft">Geburtsdatum:</p>
						<p class="pdRight">16.05.1988</p>
						<p class="pdLeft">Zivilstand:</p>
						<p class="pdRight">ledig, keine Kinder</p>
				</div>
			</div>
		</div>
	</article>


</body>


</html>
