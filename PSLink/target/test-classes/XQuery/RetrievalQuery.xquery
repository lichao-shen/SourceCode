declare namespace ps = "http://pshealth.co.uk";
declare variable $in.headers.msgId as xs:string external;
declare variable $in.headers.currentDateTime as xs:string external;

<soap:Envelope xmlns:hl7="urn:hl7-org:v3" xmlns:wsa="http://schemas.xmlsoap.org/ws/2004/08/addressing" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	<soap:Header>
		<wsa:MessageID>{$in.headers.msgId}</wsa:MessageID>
		<wsa:Action>urn:nhs:names:services:pdsquery/QUPA_IN000008UK02</wsa:Action>
		<wsa:To>https://msg.dev.spine2.ncrs.nhs.uk/sync-service</wsa:To>
		<wsa:From>
			<wsa:Address>10.204.139.61</wsa:Address>
		</wsa:From>
		<hl7:communicationFunctionRcv>
			<hl7:device>
				<hl7:id root="1.2.826.0.1285.0.2.0.107" extension="928942012545"/>
			</hl7:device>
		</hl7:communicationFunctionRcv>
		<hl7:communicationFunctionSnd>
			<hl7:device>
				<hl7:id root="1.2.826.0.1285.0.2.0.107" extension="200000000183"/>
			</hl7:device>
		</hl7:communicationFunctionSnd>
		<wsa:ReplyTo>
			<wsa:Address>10.204.139.61</wsa:Address>
		</wsa:ReplyTo>
	</soap:Header>
	<soap:Body>
		<QUPA_IN000008UK02 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:voc="urn:hl7-org:v3/voc" xmlns:msg="urn:hl7-org:v3/mif" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns="urn:hl7-org:v3">
			<id root='{$in.headers.msgId}'/>
			<creationTime value='{$in.headers.currentDateTime}'/>
			<versionCode code="3NPfIT6.3.00"/>
			<interactionId root="2.16.840.1.113883.2.1.3.2.4.12" extension="QUPA_IN000008UK02"/>
			<processingCode code="P"/>
			<processingModeCode code="T"/>
			<acceptAckCode code="NE"/>
			<communicationFunctionRcv typeCode="RCV">
				<device classCode="DEV" determinerCode="INSTANCE">
					<id root="1.2.826.0.1285.0.2.0.107" extension="928942012545"/>
				</device>
			</communicationFunctionRcv>
			<communicationFunctionSnd typeCode="SND">
				<device classCode="DEV" determinerCode="INSTANCE">
					<id root="1.2.826.0.1285.0.2.0.107" extension="200000000183"/>
				</device>
			</communicationFunctionSnd>
			<ControlActEvent classCode="CACT" moodCode="EVN">
				<author typeCode="AUT">
					<AgentPersonSDS classCode="AGNT">
						<id root="1.2.826.0.1285.0.2.0.67" extension="102048607984"/>
						<agentPersonSDS classCode="PSN" determinerCode="INSTANCE">
							<id root="1.2.826.0.1285.0.2.0.65" extension="287246644549"/>
						</agentPersonSDS>
						<part typeCode="PART">
							<partSDSRole classCode="ROL">
								<id root="1.2.826.0.1285.0.2.1.104" extension="S0080:G0440:R5090"/>
							</partSDSRole>
						</part>
					</AgentPersonSDS>
				</author>
				<author1 typeCode="AUT">
					<AgentSystemSDS classCode="AGNT">
						<agentSystemSDS classCode="DEV" determinerCode="INSTANCE">
							<id root="1.2.826.0.1285.0.2.0.107" extension="200000000183"/>
						</agentSystemSDS>
					</AgentSystemSDS>
				</author1>
				<query>
					{
						<historicDataIndicator>
							<semanticsText>HistoricDataIndicator</semanticsText>
							<value code='{/ps:RetrievalQuery/ps:historicDataIndicator}' codeSystem="2.16.840.1.113883.2.1.3.2.4.17.36"/>
						</historicDataIndicator>						
						,					
						<person.id>
							<semanticsText>Person.id</semanticsText>
							{
								if (/ps:RetrievalQuery/ps:NHSNumber/ps:identifier/text() eq 'NHS number') then (
									<value root='2.16.840.1.113883.2.1.4.1' extension='{/ps:RetrievalQuery/ps:NHSNumber/ps:value}'/>
								) else (
									<value root='2.16.840.1.113883.2.1.4.3' extension='{/ps:RetrievalQuery/ps:NHSNumber/ps:value}'/>
								)							
							}
						</person.id>
						,
						<retrievalItem>						
							<semanticsText>/ps:RetrievalQuery/ps:retrievalItem/text()</semanticsText>
						</retrievalItem>					
					}
					
				</query>
			</ControlActEvent>
		</QUPA_IN000008UK02>
	</soap:Body>
</soap:Envelope>