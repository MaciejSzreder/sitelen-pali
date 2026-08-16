// CheerpJ initialization utilities
let ready = false;

async function ensureCheerpJ(options = {}) {
  if (ready) return;
  const config = {
    version: 17,
    status: 'none',
    ...options
  };
  await cheerpjInit(config);
  ready = true;
}

// Error message formatting helper
function formatErrorMessage(error) {
  return typeof error === 'string' ? error : (error && error.message) ? error.message : String(error);
}

// Console capture utility for Java applications
async function captureConsole(action) {
  const capturedOutput = [];
  const originalConsoleLog = console.log;
  const originalConsoleError = console.error;
  
  console.log = function(...args) {
    capturedOutput.push({ type: 'log', message: args.map(arg => 
      typeof arg === 'object' ? JSON.stringify(arg) : String(arg)
    ).join(' ') });
  };
  
  console.error = function(...args) {
    capturedOutput.push({ type: 'error', message: args.map(arg => 
      typeof arg === 'object' ? JSON.stringify(arg) : String(arg)
    ).join(' ') });
  };
  
  try {
    await action();
  } finally {
    console.log = originalConsoleLog;
    console.error = originalConsoleError;
  }
  
  return capturedOutput.map(entry => {
    const prefix = entry.type === 'error' ? 'ERROR: ' : '';
    return prefix + entry.message;
  }).join('\n');
}

// File creation utility for /str/ filesystem
async function createInputFile(content) {
  const strPath = '/str/input.txt';
  cheerpOSAddStringFile(strPath, content);
  return strPath;
}