<!DOCTYPE html>

<html>
<head>
<title>Home</title>
	<?php
    		$this->Html->css('loginPage', array('inline' => false));
 
	?>

</head>

<body>

<div class="users form">
<?php echo $this->Session->flash('auth'); ?>
<?php echo $this->Form->create('User'); ?>
    <fieldset>
        <legend>
            <?php echo __('Please enter the password'); ?>
        </legend>
        <?php echo $this->Form->input('username', array('type' => 'hidden', 'default' => 'user'));
        echo $this->Form->input('password', array('label' => false));
    ?>
    </fieldset>
<?php echo $this->Form->end(__('Login')); ?>
</div>

</body>
</html>