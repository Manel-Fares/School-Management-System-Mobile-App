<?php

namespace schoolBundle\Controller;

use schoolBundle\Form\ContactType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Swift_Mailer;
use Swift_SmtpTransport;
class DefaultController extends Controller
{
    public function indexAction()
    {
        $snappy = $this->get('knp_snappy.pdf');

        $html = $this->renderView('', array(
            //..Send some data to your view if you need to //
        ));

        $filename = 'myFirstSnappyPDF';

        return new Response(
            $snappy->getOutputFromHtml($html),
            200,
            array(
                'Content-Type'          => 'application/pdf',
                'Content-Disposition'   => 'inline; filename="'.$filename.'.pdf"'
            )
        );
    }

    public function mapAction(Request $request)
    {
        $form = $this->createForm(ContactType::class,null,array(
            // To set the action use $this->generateUrl('route_identifier')
            'action' => $this->generateUrl('school_map'),
            'method' => 'POST'
        ));

        if ($request->isMethod('POST')) {
            // Refill the fields in case the form is not valid.
            $form->handleRequest($request);

            if($form->isValid()){
                $email=$form['email']->getData();
                $transporter = Swift_SmtpTransport::newInstance('smtp.gmail.com', 465, 'ssl')
                    ->setUsername('velov638@gmail.com')
                    ->setPassword('Velo123456789.');
                $mailer = Swift_Mailer::newInstance($transporter);
                $subject=$form['subject']->getData();
                $msg=$form['message']->getData();
                $name=$form['name']->getData();


                $message = (new \Swift_Message($subject))
                    ->setFrom($email)
                    ->setTo('manel.fares@esprit.tn')
                    ->setBody('username '.$name."\n".'email '.$email."\n".$msg)
                ;
                $mailer->send($message);



            }
        }

        return $this->render('@school/Default/map.html.twig', array(
            'form' => $form->createView()
        ));
    }




}
