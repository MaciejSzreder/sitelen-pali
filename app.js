async function runDemo() {
  const output = document.getElementById('output');
  const input = document.getElementById('input').value;
  
  if (!input.trim()) {
    output.textContent = 'Please enter some text in the input area.';
    return;
  }

  output.textContent = 'Starting CheerpJ…';

  try {
    await ensureCheerpJ();
    output.textContent = 'Creating input file…';
    
    const inputFilePath = await createInputFile(input);
    output.textContent = 'Launching IloPiSitelenPali with input file…';
    
    // Capture console output while running the Java application
    const capturedText = await captureConsole(async () => {
      await cheerpjRunMain('IloPiSitelenPali', '/app/sitelen-pali/IloPiSitelenPali.jar', '--input', inputFilePath);
    });
    
    // Display the captured output
    if (capturedText.trim()) {
      output.textContent = capturedText;
    } else {
      output.textContent = 'IloPiSitelenPali completed. No output was captured.';
    }
  } catch (error) {
    output.textContent = 'CheerpJ could not start the Java app: ' + formatErrorMessage(error);
  }
}

window.addEventListener('load', () => {
  ensureCheerpJ().catch((error) => {
    document.getElementById('output').textContent = 'CheerpJ initialization failed: ' + formatErrorMessage(error);
  });
});